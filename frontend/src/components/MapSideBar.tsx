import React from "react";

type District = {
    id: number,
    districtName: string,
    districtNumber: number
};

type MapSideBarProps = {
    districts: District[];
    handleDistrictChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    selectedDistrict: string | null;
};
export default function MapSideBar({districts,  handleDistrictChange, selectedDistrict} : MapSideBarProps) {
    return(
        <aside className="flex justify-evenly h-[40vh]">
            <select className="h-8 rounded-full bg-wovie pl-2 text-[2.3vh] text-third" onChange={handleDistrictChange} value={selectedDistrict || ''}>
                <option className="" value="">Wähle einen Bezirk</option>
                {districts.map(district => (
                    <option key={district.id} value={district.districtName}>{district.districtName}</option>
                ))}
            </select>
        </aside>
    )
}