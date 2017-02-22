package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;


public class Gol implements Parcelable {
    private String nome;
    private String time;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.time);
    }

    public Gol() {
    }

    protected Gol(Parcel in) {
        this.nome = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<Gol> CREATOR = new Parcelable.Creator<Gol>() {
        @Override
        public Gol createFromParcel(Parcel source) {
            return new Gol(source);
        }

        @Override
        public Gol[] newArray(int size) {
            return new Gol[size];
        }
    };
}