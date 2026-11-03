package duel;

/** แมพที่เลือกได้จากหน้าเมนู */
public enum MapType {
    CLASSIC("Classic Arena"),
    WALLS("Sliding Walls"),
    LAVA("Rising Lava");

    private final String label;

    MapType(String label) { this.label = label; }

    @Override public String toString() { return label; }
}
