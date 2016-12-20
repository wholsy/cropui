package com.yueny.blog.service.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.yueny.blog.service.disruptor.event.LogEvent;

public class LogEventProducer {
	// private static final EventTranslatorOneArg<LogEvent, ByteBuffer>
	// TRANSLATOR = new EventTranslatorOneArg<LogEvent, ByteBuffer>() {
	// @Override
	// public void translateTo(final LogEvent event, final long sequence, final
	// ByteBuffer bb) {
	// event.set(bb.toString());
	// }
	// };

	private final RingBuffer<LogEvent> ringBuffer;

	public LogEventProducer(final RingBuffer<LogEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(final ByteBuffer bb) {
		ringBuffer.publishEvent((event, sequence) -> event.set(bb.toString()));

		// or
		// ringBuffer.publishEvent(TRANSLATOR, bb);

		// or
		// final long sequence = ringBuffer.next(); // Grab the next sequence
		// try {
		// final LogEvent event = ringBuffer.get(sequence); // Get the entry in
		// // the Disruptor
		// // for the sequence
		// event.set(bb.toString()); // Fill with data
		// } finally {
		// ringBuffer.publish(sequence);
		// }
	}
}
