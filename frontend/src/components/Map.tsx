import React, {useEffect, useState} from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-extra-markers';
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

    const getMarkerColor = (wasteType: string) => {
        switch (wasteType) {
            case 'paperWaste':
                return 'red';
            case 'organicWaste':
                return '#6F4E37';
            case 'metalWaste':
                return 'black';
            case 'glassWaste':
                return 'green';
            case 'plasticWaste':
                return 'yellow';
            default:
                return 'blue';
        }
    };

    type MarkerColor = "black" | "green" | "yellow" | "blue" | "red" | "orange-dark" | "orange" | "blue-dark" | "cyan" | "purple" | "violet" | "pink" | "green-dark" | "green-light" | "white" | `#${string}`;

    const createMarkerIcon = (color: MarkerColor) => {
        return L.ExtraMarkers.icon({
            icon: 'fa-circle',
            markerColor: color,
            svg: true,
            shape: 'circle',
            prefix: 'fa'
        });
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
                <MapContainer className="h-[70vh] w-[80vw]" center={[48.208492, 16.373127]} zoom={13} scrollWheelZoom={true}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    {containers.flatMap(container => (
                        Object.entries(container).flatMap(([key, value]) => {
                            if (value === true && ['paperWaste', 'organicWaste', 'metalWaste', 'glassWaste', 'plasticWaste'].includes(key)) {
                                return (
                                    <Marker
                                        key={`${container.id}-${key}`}
                                        position={[container.longitude, container.latitude]}
                                        icon={createMarkerIcon(getMarkerColor(key))}>
                                        <Popup>
                                            {/* Popup content, perhaps include container.street and the waste type */}
                                            {container.street + " " + container.streetNumber + ", " + container.districtNumber + ". " + container.districtName}
                                        </Popup>
                                    </Marker>
                                );
                            } else {
                                return [];
                            }
                        })
                    ))}
                </MapContainer>
            </div>
        </div>
    );

}
