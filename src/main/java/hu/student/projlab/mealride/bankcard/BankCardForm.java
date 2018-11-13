package hu.student.projlab.mealride.bankcard;

public class BankCardForm {

    private Long number;
    private String ownername;
    private String expriationmonth;
    private String expriationyear;
    private String cvc; // hashed

    public BankCardForm() {
    }

    public BankCardForm(Long number, String ownername, String expriationmonth, String expriationyear, String cvc) {
        this.number = number;
        this.ownername = ownername;
        this.expriationmonth = expriationmonth;
        this.expriationyear = expriationyear;
        this.cvc = cvc;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getExpriationmonth() {
        return expriationmonth;
    }

    public void setExpriationmonth(String expriationmonth) {
        this.expriationmonth = expriationmonth;
    }

    public String getExpriationyear() {
        return expriationyear;
    }

    public void setExpriationyear(String expriationyear) {
        this.expriationyear = expriationyear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
