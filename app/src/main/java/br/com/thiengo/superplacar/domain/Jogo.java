package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;


public class Jogo implements Parcelable {
    public static final String JOGOS_KEY = "jogos_key";

    private Time time1;
    private Time time2;
    private String status;
    private String inicio;

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.time1, flags);
        dest.writeParcelable(this.time2, flags);
        dest.writeString(this.status);
        dest.writeString(this.inicio);
    }

    public Jogo() {
    }

    protected Jogo(Parcel in) {
        this.time1 = in.readParcelable(Time.class.getClassLoader());
        this.time2 = in.readParcelable(Time.class.getClassLoader());
        this.status = in.readString();
        this.inicio = in.readString();
    }

    public static final Parcelable.Creator<Jogo> CREATOR = new Parcelable.Creator<Jogo>() {
        @Override
        public Jogo createFromParcel(Parcel source) {
            return new Jogo(source);
        }

        @Override
        public Jogo[] newArray(int size) {
            return new Jogo[size];
        }
    };
}
