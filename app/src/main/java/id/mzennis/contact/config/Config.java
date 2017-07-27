package id.mzennis.contact.config;

import javax.inject.Singleton;

import dagger.Component;
import id.mzennis.contact.base.BaseFragment;
import id.mzennis.contact.network.NetworkModule;

/**
 * Created by mzennis on 7/26/17.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Config {
    void inject(BaseFragment fragment);
}
