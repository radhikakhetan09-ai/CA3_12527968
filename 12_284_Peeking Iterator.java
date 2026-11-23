class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextItem;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (this.iterator.hasNext()) {
            nextItem = this.iterator.next();
        }
    }

    public Integer peek() {
        return nextItem;
    }

    @Override
    public Integer next() {
        Integer current = nextItem;
        if (iterator.hasNext()) {
            nextItem = iterator.next();
        } else {
            nextItem = null;
        }
        return current;
    }

    @Override
    public boolean hasNext() {
        return nextItem != null;
    }
}
