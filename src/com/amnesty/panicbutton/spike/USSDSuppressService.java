package com.amnesty.panicbutton.spike;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.telephony.IExtendedNetworkService;

public class USSDSuppressService extends Service {
    private String TAG = USSDSuppressService.class.getSimpleName();
    private boolean mActive = false; //we will only activate this "USSD listener" when we want it

    @Override
    public void onCreate() {
        Log.v("USSDSuppressService", "funny ... USSDSuppressService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("USSDSuppressService", "funny ... onBind Called");
        return mBinder;
    }

    private final IExtendedNetworkService.Stub mBinder = new IExtendedNetworkService.Stub() {
        public void clearMmiString() throws RemoteException {
            Log.v(TAG, "called clear");
        }

        public void setMmiString(String number) throws RemoteException {
            Log.v(TAG, "setMmiString:" + number);
        }

        public CharSequence getMmiRunningText() throws RemoteException {
            return mActive ? null : "USSD Running";
        }

        public CharSequence getUserMessage(CharSequence text)
                throws RemoteException {
            Log.v(TAG, "get user message " + text);
            return null;
        }
    };
}