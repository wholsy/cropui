package com.yueny.blog.service.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.yueny.blog.service.disruptor.event.LogEvent;
import com.yueny.blog.service.disruptor.factory.LogEventFactory;
import com.yueny.blog.service.disruptor.handler.LogEventHandler;
import com.yueny.cropui.service.initdata.NamedThreadFactory;

public class LogEventMain {
	public static void main(final String[] args) throws Exception {
		// Executor that will be used to construct new threads for consumers
		// final Executor executor = Executors.newCachedThreadPool();

		// The factory for the event
		final LogEventFactory factory = new LogEventFactory();

		// Specify the size of the ring buffer, must be power of 2.
		final int bufferSize = 1024;

		// Construct the Disruptor
		final Disruptor<LogEvent> disruptor = new Disruptor<>(factory, bufferSize,
				new NamedThreadFactory("MinaClientWorker", true),
				// Single producer
				ProducerType.SINGLE, new BlockingWaitStrategy());

		// Connect the handler
		disruptor.handleEventsWith(new LogEventHandler());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		// Get the ring buffer from the Disruptor to be used for publishing.
		final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

		final LogEventProducer producer = new LogEventProducer(ringBuffer);

		final ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0; true; l++) {
			bb.putLong(0, l);

			// producer.onData(bb);
			ringBuffer.publishEvent((event, sequence) -> event.set(bb.toString()));

			Thread.sleep(1000);
		}
	}
}
