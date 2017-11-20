package com.example.pedestrian.homeservicerobotofneu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Dialog for requesting confirmation before deleting a RobotInfo.
 *
 * Created by Michael Brunson on 1/23/16.
 */
public class ConfirmDeleteDialogFragment extends DialogFragment {

    /** Bundle key for the name of the Robot being deleted */
    public static final String NAME_KEY = "DELETE_ITEM_NAME_KEY";
    /** Bundle key for the position of the RobotItem being deleted */
    public static final String POSITION_KEY = "DELETE_ITEM_POSITION_KEY";

    private DialogListener mListener;
    private String mItemName;
    private int mPosition;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mItemName = args.getString(NAME_KEY);
        mPosition = args.getInt(POSITION_KEY);
    }

    // Override the Fragment.onAttach() method to instantiate the DialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            //throw new ClassCastException(activity.toString() + " must implement DialogListener");
        }
    }

    /**
     * Creates the Dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Delete")
                .setMessage("Delete: " + "'" + mItemName + "'" + "?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Notify the listener
                        mListener.onConfirmDeleteDialogPositiveClick(mPosition);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Notify the listener
                        mListener.onConfirmDeleteDialogNegativeClick();
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    /**
     * Interface for Objects that need to be notified of the result of the user's action on the Dialog.
     */
    public interface DialogListener {
        void onConfirmDeleteDialogPositiveClick(int position);
        void onConfirmDeleteDialogNegativeClick();
    }
}
