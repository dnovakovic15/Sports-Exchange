package fantasy.nfl.tool.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darko on 11/21/2016.
 */

class User implements Parcelable {

    private String UserName;
    private String  Password;
    private int Action;


    public User(String name,String pass,int ac){
        UserName=name;
        Password=pass;
        Action=ac;
    }


    //parcel part
    public User(Parcel in){
        String[] data= new String[3];

        in.readStringArray(data);
        this.UserName= data[0];
        this.Password= data[1];
        this.Action= Integer.parseInt(data[2]);
    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.UserName,this.Password,String.valueOf(this.Action)});
    }

    public static final Parcelable.Creator<User> CREATOR= new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new User(source);  //using parcelable constructor
        }

        @Override
        public User[] newArray(int size) {
// TODO Auto-generated method stub
            return new User[size];
        }
    };

}
