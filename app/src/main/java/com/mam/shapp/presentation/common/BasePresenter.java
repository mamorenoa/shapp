package com.mam.shapp.presentation.common;

public interface BasePresenter {
	void onViewAttached(BaseView view);
	void onViewDetached();
}
