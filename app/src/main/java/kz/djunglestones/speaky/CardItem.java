package kz.djunglestones.speaky;

/**
 * Created by yelaman on 21.07.17.
 */

public class CardItem {

    private int mTextResource;
    private int mTitleResource;

    public CardItem(int title, int text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public void setTextResource(int mTextResource) {
        this.mTextResource = mTextResource;
    }

    public void setTitleResource(int mTitleResource) {
        this.mTitleResource = mTitleResource;
    }
}
