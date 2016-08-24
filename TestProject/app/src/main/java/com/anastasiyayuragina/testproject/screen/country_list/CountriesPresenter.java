package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.v4.util.ArrayMap;
import com.anastasiyayuragina.testproject.ItemCountry;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.ourDataBase.CountryComment;
import com.anastasiyayuragina.testproject.ourDataBase.CountryComment_Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import java.util.List;
import java.util.Map;


/**
 * Created by anastasiyayuragina on 8/2/16.
 *
 */
public class CountriesPresenter implements CountriesMvp.Presenter, CountriesMvp.Model.OnDataLoaded {

    private CountriesMvp.Model model;
    private CountriesMvp.View view;
    private ItemCountry itemCountry = null;
    private boolean dataLoaded = false;
    private Map<String, String> com = new ArrayMap<>();


    public CountriesPresenter(CountriesMvp.Model model, CountriesMvp.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadData() {
        if (itemCountry == null) {
            model.loadData(1, this);
        } else {
            view.showLoadMore();
            int page = itemCountry.getPageInfo().getPage() + 1;
            if (page <= itemCountry.getPageInfo().getPages()){
                dataLoaded = false;
                model.loadData(page, this);
            }
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onDataLoaded(ItemCountry itemCountry) {
        this.itemCountry = itemCountry;

        List<Country> country = itemCountry.getCountryList();

        for (int i = 0; i < country.size(); i++) {
            CountryComment comment = new Select(CountryComment_Table.comment).from(CountryComment.class).where(CountryComment_Table.id_country.is(country.get(i).getId())).querySingle();
            if (comment != null) {
                com.put(country.get(i).getId(), comment.getComment());
            }
        }

        for (int i = 0; i < country.size(); i++) {
            country.get(i).setComment(com.get(country.get(i).getId()));
        }

        if(view != null){
            view.setData(country);
            dataLoaded = true;
        }
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }
}
