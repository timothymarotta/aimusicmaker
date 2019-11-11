public class Note {
    int startPosition;
    String note;
    int length;
    int instrumentId;

    public Note(int startPosition, String note, int length, int instrumentId){
        this.startPosition = startPosition;
        this.note = note;
        this.length = length;
        this.instrumentId = instrumentId;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }
}
