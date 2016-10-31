/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.longngohoang.twitter.appcore.interactor;


import com.longngohoang.twitter.appcore.common.schedulers.BaseSchedulerProvider;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public abstract class UseCase {

  private final BaseSchedulerProvider   baseSchedulerProvider;

  private Subscription subscription = Subscriptions.empty();

  protected UseCase(BaseSchedulerProvider baseSchedulerProvider) {
    this.baseSchedulerProvider = baseSchedulerProvider;
  }


  protected abstract Observable buildUseCaseObservable(Object ...objects);


  @SuppressWarnings("unchecked")
  public void execute(Subscriber useCaseSubscriber,Object ...objects) {
    this.subscription = this.buildUseCaseObservable(objects)
            .subscribeOn( baseSchedulerProvider.computation())
            .observeOn( baseSchedulerProvider.ui())
        .subscribe(useCaseSubscriber);
  }


  public void unsubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }
}
