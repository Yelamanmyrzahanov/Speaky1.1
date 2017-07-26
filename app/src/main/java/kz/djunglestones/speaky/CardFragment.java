package kz.djunglestones.speaky;

/**
 * Created by yelaman on 21.07.17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CardFragment extends Fragment {

    private CardView mCardView;
    private Button buttonEasyStart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adapter_card_view, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        buttonEasyStart= (Button)view.findViewById(R.id.button_start_easy);
//        buttonEasyStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(),GameEasyTwisters.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }
}
