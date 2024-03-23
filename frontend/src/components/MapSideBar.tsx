import React from "react";

type District = {
    id: number,
    districtName: string,
    districtNumber: number
};

type MapSideBarProps = {
    districts: District[];
    handleDistrictChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    handleWasteTypeChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    selectedDistrict: string | null;
};
export default function MapSideBar({districts,  handleDistrictChange, selectedDistrict, handleWasteTypeChange} : MapSideBarProps) {
    const wasteTypes = [{english: "paperWaste", german: "Papier"}, {english: "organicWaste", german: "BIO"}, {english: "plasticWaste", german: "Verpackung"}, {english: "glassWaste", german: "Glas"}];

    return(
        <aside className="grid grid-cols-1 gap-y-3">
            <select className="h-8 rounded-full bg-wovie pl-2 text-[2.3vh] text-third" onChange={handleDistrictChange} value={selectedDistrict || ''}>
                <option className="" value="">Wähle einen Bezirk</option>
                {districts.map(district => (
                    <option key={district.id} value={district.districtName}>{district.districtName}</option>
                ))}
            </select>
            <select className="h-8 rounded-full bg-wovie pl-2 text-[2.3vh] text-third" onChange={handleWasteTypeChange} value={selectedDistrict || ''}>
                <option className="" value="">Wähle eine Kategorie</option>
                {wasteTypes.map(({english, german}, index) => (
                    <option key={index} value={english}>{german}</option>
                ))}
            </select>
        </aside>
    )
}