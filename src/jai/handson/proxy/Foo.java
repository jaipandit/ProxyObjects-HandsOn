package jai.handson.proxy;

/**
 * A Class whose object we'd want to proxy in this demo.
 * @author pandj009
 *
 */
public class Foo implements StringInstrument{

	@Override
	public void play() {
		System.out.println("Playing from " + this.getClass().getSimpleName());
	}

	@Override
	public void strum() {
		play();
	}
	
}
