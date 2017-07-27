package id.mzennis.contact.base;

import android.content.Context;
import android.view.View;

/**
 * Created by mzennis on 7/26/17.
 */

public interface IBasePresenter {
    Context getContext();
    void onCreate(Context context);
    void onResume();
    void onPaused();
    void onDestroy();
    void onAttach(Context context);
    void onCreateView(View view);
    void onViewCreated(View view);
}
