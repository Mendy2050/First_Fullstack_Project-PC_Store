package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/** A persistent layer interface that handles state/city data */
public interface DistrictMapper {

    /**
     * Get all provinces in the country, all cities in a province, and all districts in a city
     * @param parent Parent code, when obtaining all districts of a city, use the code name of the city; When obtaining all municipalities in the province, use the code name of the province; When acquiring all provinces in the country, use "86" as the parent code
     * @return A list of all provinces in the country, all cities in a province, and all districts in a city
     */
    List<District> findByParent(String parent);



    /**
     * Get the name of the province, city, district according to the administrative code of the province, city, district
     * @param code The administrative code of the state/city/district
     * @returnThe name of the matched state, city or district, or null if there is no matching data
     */
    String findNameByCode(String code);

}
