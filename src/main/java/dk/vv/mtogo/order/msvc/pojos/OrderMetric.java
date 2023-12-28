package dk.vv.mtogo.order.msvc.pojos;

public class OrderMetric {

    private long finishedCount;
    private long errorCount;
    private long receivedCount;
    private long acceptedCount;
    private long deniedCount;

    private long totalCount;

    public OrderMetric(long errorCount, long receivedCount, long acceptedCount, long finishedCount, long deniedCount, long totalCount) {
        this.finishedCount = finishedCount;
        this.errorCount = errorCount;
        this.receivedCount = receivedCount;
        this.acceptedCount = acceptedCount;
        this.deniedCount = deniedCount;
        this.totalCount = totalCount;
    }

    public long getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(long finishedCount) {
        this.finishedCount = finishedCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(long receivedCount) {
        this.receivedCount = receivedCount;
    }

    public long getAcceptedCount() {
        return acceptedCount;
    }

    public void setAcceptedCount(long acceptedCount) {
        this.acceptedCount = acceptedCount;
    }

    public long getDeniedCount() {
        return deniedCount;
    }

    public void setDeniedCount(long deniedCount) {
        this.deniedCount = deniedCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "OrderMetric{" +
                "finishedCount=" + finishedCount +
                ", errorCount=" + errorCount +
                ", receivedCount=" + receivedCount +
                ", acceptedCount=" + acceptedCount +
                ", deniedCount=" + deniedCount +
                ", totalCount=" + totalCount +
                '}';
    }
}
