import React from "react";

type District = {
    id: number,
    districtName: string,
    districtNumber: number
};

type MapSideBarProps = {
    districts: District[];
    handleDistrictChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    handleWasteTypeChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    handleSubmit: (event: React.FormEvent<HTMLFormElement>) => void;
    selectedDistrict: string | null;
};
export default function MapFilterSection({districts, handleDistrictChange, selectedDistrict, handleWasteTypeChange, handleSubmit,}: MapSideBarProps) {
    const wasteTypes = [{english: "paperWaste", german: "Papier"}, {
        english: "organicWaste",
        german: "BIO"
    }, {english: "plasticWaste", german: "Verpackung"}, {english: "glassWaste", german: "Glas"}];

    return (
        <form className="flex flex-col sm:flex-row w-full pl-[2.5vw] sm:items-center"
              onSubmit={handleSubmit}>
            <select className="h-[4vh] rounded-full bg-wovie pl-2 text-[2.3vh] text-third w-[55vw] sm:w-[25vw]"
                    onChange={handleDistrictChange}
                    value={selectedDistrict || ''}>
                <option className="" value="">Wähle einen Bezirk</option>
                {districts.map(district => (
                    <option key={district.id} value={district.districtName}>{district.districtName}</option>
                ))}
            </select>
            <div className="flex flex-col sm:flex-row mt-[2vh] sm:mt-[0vh] md:ml-[2.5vw] ">
                {wasteTypes.map(({english, german}, index) => {
                    const color = english == "paperWaste" ? "#E20B1E" :
                        english == "organicWaste" ? "#924C1A" :
                            english == "plasticWaste" ? "#FDE306" :
                                english == "glassWaste" ? "#2F5924" : "third";

                    return (<div key={index} className="pl-[0.5vw] flex items-center">
                        <input onChange={handleWasteTypeChange} type="checkbox" className="border-2 ml-[0.5vw]" value={english}/>
                        <label
                            className="ml-3 text-amber-50 font-light text-[2vh] sm:text-[2vh] md:text-[2.5vh]">{german}</label>
                        <div style={{backgroundColor: color}}
                             className="ml-[1vw] md:ml-[0.5vw] rounded-full h-[2vh] w-[2vh] md:h-[2.5vh] md:w-[2.5vh]"></div>
                    </div>);

                })}
                <button type="submit"
                        className="bg-wovie rounded-full w-[10vw] md:w-[8vw] lg:w-[6vw] h-[4vh] ml-0 mt-2 sm:mt-0 sm:ml-[2.5vw] text-third text-[1.7vh] md:text-[1.9vh] font-semibold">LOS!
                </button>
            </div>
        </form>
    )
}