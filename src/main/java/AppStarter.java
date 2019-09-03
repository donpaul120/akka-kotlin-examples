import akka.actor.ActorRef;
import akka.actor.ActorSystem;


public class AppStarter {

    public static void main(String[] args){
        ActorSystem actorSystem = ActorSystem.create("migration-bot");
        ActorRef etlManager = actorSystem.actorOf(ETLManager.props());
        etlManager.tell("Start", ActorRef.noSender());
    }

}
