public class Note {
    int startPosition;
    String pitch;
    int duration;
    int instrumentId;

    public Note(int startPosition, String pitch, int duration, int instrumentId){
        this.startPosition = startPosition;
        this.pitch = pitch;
        this.duration = duration;
        this.instrumentId = instrumentId;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }
}
