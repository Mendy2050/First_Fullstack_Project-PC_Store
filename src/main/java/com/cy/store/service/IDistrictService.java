package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

/** Business-layer interface that handle state, city, and district data */
public interface IDistrictService {


    /**
     * Get all provinces, all cities in a province, and all districts in a city
     * @param parent Parent code, when obtaining all districts of a city, use the code name of the city; When obtaining all cities in a province, use the code name of the province; When acquiring all provinces in the country, use "86" as the parent code
     * @return A list of all provinces, cities in a province, and districts in a city
     */
    List<District> getByParent(String parent);



    /**
     * Obtain the name of the province, city, and district according to the administrative code of the province, city, and district
     * @param code The administrative code of the province, city and district
     * @return The name of the matching state, city, or district, or null if there is no matching data
     */
    String getNameByCode(String code);
}
