package actor4j.benchmark.ring;

import java.util.UUID;

import actor4j.benchmark.Benchmark;
import actor4j.core.ActorMessage;
import actor4j.core.ActorSystem;

public class TestRing {
	public TestRing() {
		ActorSystem system = new ActorSystem();
		//system.setParallelismMin(1);
		system.setParallelismFactor(1);
		system.hardMode();
		
		int size = 100;
		UUID next = system.addActor(new Forwarder(null));
		for(int i=0; i<size-2; i++) {
			next = system.addActor(new Forwarder(next));
		}
		UUID sender = system.addActor(new Sender(next));
		
		system.send(new ActorMessage<>(new Object(), 0, sender, sender));
		
		
		Benchmark benchmark = new Benchmark(system, 60000);
		benchmark.start();
	}
	
	public static void main(String[] args) {
		new TestRing();
	}
}
