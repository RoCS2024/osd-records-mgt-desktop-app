package com.rc.porms;


import com.rc.porms.appl.facade.prefect.offense.OffenseFacade;
import com.rc.porms.appl.facade.prefect.offense.impl.OffenseFacadeImpl;
import com.rc.porms.data.prefect.offense.OffenseDao;
import com.rc.porms.data.prefect.offense.impl.OffenseDaoImpl;

public class PrefectInfoMgtApplication {
    private OffenseFacade offenseFacade;

    /**
     * This creates a new com.prefect.information.management.PrefectInfoMgtApplication
     *
     * @return the PrefectFacade this helps for managing student data.
     */

    public PrefectInfoMgtApplication(){

        OffenseDao offenseDaoImpl = new OffenseDaoImpl();
        this.offenseFacade = new OffenseFacadeImpl(offenseDaoImpl);

    }
    public OffenseFacade getOffenseFacade() {
        return offenseFacade;
    }

}

