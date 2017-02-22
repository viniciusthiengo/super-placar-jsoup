package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class Time implements Parcelable {
    private String nome;
    private String imagemUrl;
    private int gols;
    private List<Gol> golsLista;


    public Time(){
        golsLista = new ArrayList<>();
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

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public List<Gol> getGolsLista() {
        return golsLista;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.imagemUrl);
        dest.writeInt(this.gols);
        dest.writeTypedList(this.golsLista);
    }

    protected Time(Parcel in) {
        this.nome = in.readString();
        this.imagemUrl = in.readString();
        this.gols = in.readInt();
        this.golsLista = in.createTypedArrayList(Gol.CREATOR);
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