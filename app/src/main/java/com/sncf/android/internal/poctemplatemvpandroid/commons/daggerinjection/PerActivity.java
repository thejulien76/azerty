package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *
 * Scope permettant d'encadrer l'accès
 *
 * @author PNVE12891
 * @version 1.0
 * @since 15/03/2018
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface PerActivity {
}
