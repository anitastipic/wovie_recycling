import React, {useEffect, useState} from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-extra-markers/dist/css/leaflet.extra-markers.min.css';
import 'leaflet-extra-markers/dist/js/leaflet.extra-markers.min.js';


type Container = {
    id: number,
    latitude: number,
    longitude: number,
    districtNumber: number,
    districtId: number,
    districtName: string,
    street: string,
    streetNumber: string,
    paperWaste: boolean,
    organicWaste: boolean,
    metalWaste: boolean,
    glassWaste: boolean,
    plasticWaste: boolean
};

type District = {
    id: number,
    districtName: string,
    districtNumber: number
};

export default function Map() {
    const [containers, setContainers] = useState<Container[]>([]);
    const [districts, setDistricts] = useState<District[]>([]);
    const [selectedDistrict, setSelectedDistrict] = useState<string | null>(null);

    const fetchDistricts = () => {
        return fetch("http://localhost:8080/district")
            .then((res) => res.json());
    };

    const fetchContainersByDistrict = (name: string) => {
        return fetch(`http://localhost:8080/container/district/name/${name}`)
            .then((res) => res.json());
    };


    useEffect(() => {
        fetchDistricts().then(setDistricts);
    }, []);

    const handleDistrictChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        if (event.target) {
            const districtName = event.target.value
            setSelectedDistrict(districtName);

            if (districtName) {
                fetchContainersByDistrict(districtName).then(setContainers);
            }
        }
    };

    return (
        <div>
            <select onChange={handleDistrictChange} value={selectedDistrict || ''}>
                <option value="">All Districts</option>
                {districts.map(district => (
                    <option key={district.id} value={district.districtName}>{district.districtName}</option>
                ))}
            </select>

            <div id="map" className="h-screen flex items-center justify-center">
                <MapContainer className="h-[70vh] w-[80vw]" center={[48.208492, 16.373127]} zoom={13}
                              scrollWheelZoom={true}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    {containers.map((container) => (
                        <Marker key={container.id} position={[container.longitude, container.latitude]}>
                            <Popup>
                                {container.street} {container.streetNumber}<br/>
                                {container.districtName}
                            </Popup>
                        </Marker>
                    ))}
                </MapContainer>
            </div>
        </div>
    );
}
