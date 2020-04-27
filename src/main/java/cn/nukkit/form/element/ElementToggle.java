package cn.nukkit.form.element;

import com.google.gson.annotations.SerializedName;

public class ElementToggle extends Element {

    @SuppressWarnings("unused")
    private final String type = "toggle";
    private String text;
    @SerializedName("default")
    private boolean defaultValue;

    public ElementToggle(String text) {
        this(text, false);
    }

    public ElementToggle(String text, boolean defaultValue) {
        this.text = text;
        this.defaultValue = defaultValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
}
