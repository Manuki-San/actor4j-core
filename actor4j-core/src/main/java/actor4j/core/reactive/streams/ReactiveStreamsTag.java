/*
 * Copyright (c) 2015-2017, David A. Bauer
 */
package actor4j.core.reactive.streams;

public final class ReactiveStreamsTag {
	public static int SUBSCRIPTION_REQUEST       = 200;
	public static int SUBSCRIPTION_REQUEST_RESET = 201;
	public static int SUBSCRIPTION_CANCEL        = 202;
	public static int SUBSCRIPTION_BULK          = 203;
	public static int SUBSCRIPTION_CANCEL_BULK   = 204;
	public static int ON_NEXT                    = 205;
	public static int ON_ERROR                   = 206;
	public static int ON_COMPLETE                = 207;
}