package uz.asadbek.AdminPanel.domain;

public enum EmployeeState {
    IT_VA_DASTURLASH("IT va dasturlash"),
    TABIIY_FANLAR("Tabiiy fanlar"),
    MATEMATIKA_FIZIKA("Matematika fizika"),
    ONA_TILI_VA_ADABIYOT("Ona tili va adabiyot"),
    INGILIZ_TILI("Ingiliz tili"),
    RUS_TILI("Rus tili"),
    BOSHQA("Boshqa");

    private final String displayName;

    EmployeeState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
