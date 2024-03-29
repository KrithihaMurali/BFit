package com.krithiha.bfit;

/*
 * @(#)NotSameArraySizeException.java		1.10 05/02/24
 *
 * ChargedFluid released package
 *
 * COPYRIGHT NOTICE
 * Copyright (c) 2005
 * Laboratory of Neuro Imaging, Neurology, UCLA.
 */

/**
 * This exception will be thrown by the {@link FastFourierTransform} when the
 * size of the input arrays is not the same.
 * 
 * @version 1.10, 05/02/24
 * @author Herbert H.H. Chang and Daniel J. Valentino
 * @since JDK1.4
 */

@SuppressWarnings("serial")
public class NotSameArraySizeException extends Exception {
	public String toString() {
		return "NotSameArraySizeException[ The dimensions of input arrays are not the same! ]";
	}
}
