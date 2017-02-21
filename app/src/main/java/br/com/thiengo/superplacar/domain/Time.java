package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class Time implements Parcelable {
    private String nome;
    private String imagemUrl;
    private int goals;
    private List<Goal> goalsLista;


    public Time(){
        goalsLista = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public List<Goal> getGoalsLista() {
        return goalsLista;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.imagemUrl);
        dest.writeInt(this.goals);
        dest.writeTypedList(this.goalsLista);
    }

    protected Time(Parcel in) {
        this.nome = in.readString();
        this.imagemUrl = in.readString();
        this.goals = in.readInt();
        this.goalsLista = in.createTypedArrayList(Goal.CREATOR);
    }

    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel source) {
            return new Time(source);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };
}
