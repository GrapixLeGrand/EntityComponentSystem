package game.network;


public interface NetworkObservable {
    void notifyObserver();
    void addObserver(NetworkObserver observer);
    void removeObserver(NetworkObserver observer);
}
