package ir.maktab.homeserviceprovider.data.enums;

import lombok.Getter;

@Getter
//@AllArgsConstructor
public enum OrderState {

    WAITING_FOR_EXPERT_SUGGESTION("waiting for expert suggestion"),
    WAITING_FOR_SELECT_AN_EXPERT("waiting for select an expert"),
    WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE("waiting for expert coming to your home"),
    STARTED("started"),
    DONE("done"),
    PAID("paid");

    private String name;

    OrderState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }
}
