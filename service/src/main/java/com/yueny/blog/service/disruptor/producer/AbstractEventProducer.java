///**
// *
// */
//package com.yueny.blog.service.disruptor.producer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.lmax.disruptor.dsl.Disruptor;
//import com.yueny.blog.service.disruptor.event.IEvent;
//
//import lombok.Getter;
//
///**
// * 生产器
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2018年1月20日 下午8:58:11
// *
// */
//public abstract class AbstractEventProducer<T extends IEvent, D> {
//	/** 日志 */
//	protected final Logger logger = LoggerFactory.getLogger(getClass());
//	// private final Class<T> entityClass = extracted();
//	@Getter
//	private final Disruptor<T> disruptor;
//
//	public AbstractEventProducer(final Disruptor<T> disruptor) {
//		this.disruptor = disruptor;
//	}
//
//	// /**
//	// * @return 获取第一个泛型T的实体类信息
//	// */
//	// @SuppressWarnings("unchecked")
//	// private Class<T> extracted() {
//	// return (Class<T>) ((ParameterizedType)
//	// getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//	// }
//
//	/**
//	 * @param d
//	 *            事件传递的业务数据
//	 */
//	abstract void publishData(final D d);
//
//}
