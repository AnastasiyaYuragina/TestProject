package com.anastasiyayuragina.testproject.ourDataBase;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by anastasiyayuragina on 8/18/16.
 */
@Table(database = MyDatabase.class)

public class CountryComment extends BaseModel {

    @Column
    @PrimaryKey
    String id_country;

    @Column
    String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId_country(String id_country) {
        this.id_country = id_country;
    }

    public String getComment() {
        return comment;
    }

    public String getId_country() {
        return id_country;
    }
}
