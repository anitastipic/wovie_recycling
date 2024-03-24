import React, {useEffect, useState} from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-extra-markers';
import 'leaflet-extra-markers/dist/css/leaflet.extra-markers.min.css';
import 'leaflet-extra-markers/dist/js/leaflet.extra-markers.min.js';
import MapFilterSection from "./MapFilterSection.tsx";


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
    const [selectedWasteTypes, setSelectedWasteTypes] = useState({paperWaste:false, glassWaste: false, organicWaste:false, plasticWaste: false})

    const fetchDistricts = () => {
        return fetch("http://localhost:8080/district")
            .then((res) => res.json());
    };

    useEffect(() => {
        fetchDistricts().then(setDistricts);
    }, []);

    const fetchFilteredContainers = () => {
        const enabledWasteTypes = Object.entries(selectedWasteTypes)
            .filter(([_, value]) => value)
            .map(([key, _]) => key);

        const queryParams = new URLSearchParams();
        enabledWasteTypes.forEach(type => queryParams.append("wasteTypes", type));
        if (selectedDistrict) queryParams.set("districtName", selectedDistrict);

        const fetchURL = `http://localhost:8080/container/filter?${queryParams.toString()}`;
        return fetch(fetchURL)
            .then(res => res.json())
    };

    const handleDistrictChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const districtName = event.target.value;
        setSelectedDistrict(districtName);
    };

    const handleWasteTypeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const key = event.target.value;
        const isChecked = event.target.checked;
        setSelectedWasteTypes(prev => ({
            ...prev,
            [key]: isChecked,
        }));
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        fetchFilteredContainers().then((res: Container[]) => {
            setContainers(res);
        }).catch((error) => {
                console.error("Failed to fetch containers:", error);
            });
    }

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

    type MarkerColor =
        "black"
        | "green"
        | "yellow"
        | "blue"
        | "red"
        | "orange-dark"
        | "orange"
        | "blue-dark"
        | "cyan"
        | "purple"
        | "violet"
        | "pink"
        | "green-dark"
        | "green-light"
        | "white"
        | `#${string}`;

    const createMarkerIcon = (color: MarkerColor) => {
        return L.ExtraMarkers.icon({
            icon: 'fa-circle',
            markerColor: color,
            svg: true,
            shape: 'circle',
            prefix: 'fa'
        });
    };

    const longitudeIncrement = 0.00005;

    return (
        <div className="grid grid-cols-1 gap-y-5 place-items-center h-[84%] bg-third">
            <MapFilterSection
                districts={districts}
                selectedDistrict={selectedDistrict}
                handleDistrictChange={handleDistrictChange}
                handleWasteTypeChange={handleWasteTypeChange}
                handleSubmit={handleSubmit}/>

            <div id="map" className="">
                <MapContainer className="h-[68vh] w-[95vw]" center={[48.208492, 16.373127]} zoom={13}
                              scrollWheelZoom={true}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    {containers.reduce<React.ReactNode[]>((acc, container, index, array) => {
                        const sameLocationContainers = array.filter(c => c.latitude === container.latitude && c.longitude === container.longitude);
                        const containerIndex = sameLocationContainers.findIndex(c => c.id === container.id);
                        const adjustedLongitude = container.longitude + (containerIndex * longitudeIncrement);

                        const wasteType = container.organicWaste ? "organicWaste" :
                            container.plasticWaste ? "plasticWaste" :
                                container.paperWaste ? "paperWaste" :
                                    container.glassWaste ? "glassWaste" :
                                        container.metalWaste ? "metalWaste" : "default";
                        const markerColor = getMarkerColor(wasteType);
                        const icon = createMarkerIcon(markerColor);

                        acc.push(
                            <Marker
                                key={container.id}
                                position={[adjustedLongitude, container.latitude]}
                                icon={icon}>
                                <Popup>
                                    {container.street + " " + container.streetNumber + ", " + container.districtNumber + ". " + container.districtName}
                                </Popup>
                            </Marker>
                        );

                        return acc;
                    }, [])}
                </MapContainer>
            </div>
        </div>
    );

}
