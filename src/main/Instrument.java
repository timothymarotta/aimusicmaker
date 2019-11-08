public class Instrument {
    int instrumentId;
    int volume;
    boolean delay;
    boolean reverb;

    private void setDefaultValues(){
        this.instrumentId = 1;
        this.volume = 1;
        this.delay = false;
        this.reverb = false;
    }

    private void setRandomValues(){
        //TODO
    }

    public Instrument(){
        setDefaultValues();
    }

}
