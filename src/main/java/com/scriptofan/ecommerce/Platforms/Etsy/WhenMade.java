package com.scriptofan.ecommerce.Platforms.Etsy;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents all the values for the WhenMade field
 * in the createListing API call for Etsy.
 */
public class WhenMade {

    public static final Set<String> whenMade = new HashSet<>();

    static {
        whenMade.add("made_to_order");
        whenMade.add("2010_2018");
        whenMade.add("2000_2009");
        whenMade.add("1999_1999");
        whenMade.add("before_1999");
        whenMade.add("1990_1998");
        whenMade.add("1980s");
        whenMade.add("1970s");
        whenMade.add("1960s");
        whenMade.add("1950s");
        whenMade.add("1940s");
        whenMade.add("1930s");
        whenMade.add("1920s");
        whenMade.add("1910s");
        whenMade.add("1900s");
        whenMade.add("1800s");
        whenMade.add("1700s");
        whenMade.add("before_1700");
    }
}
