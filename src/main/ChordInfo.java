public class ChordInfo {
    String root;
    String chordType;
    int inversion;
    String extension;

    public ChordInfo(String root, String chordType){
        this(root, chordType, 0, null);
    }

    public ChordInfo(String root, String chordType, int inversion, String extension){
        this.root = root;
        this.chordType = chordType;
        this.inversion = inversion;
        this.extension = extension;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getChordType() {
        return chordType;
    }

    public void setChordType(String chordType) {
        this.chordType = chordType;
    }

    public int getInversion() {
        return inversion;
    }

    public void setInversion(int inversion) {
        this.inversion = inversion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!ChordInfo.class.isAssignableFrom(obj.getClass())){
            return false;
        }
        ChordInfo other = (ChordInfo) obj;
        //TODO: compare inversion and extension (they can be null)
        return this.getRoot().equals(other.getRoot())
                    && this.getChordType().equals(other.getChordType());

    }
}
