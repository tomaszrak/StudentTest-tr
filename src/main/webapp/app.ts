import {Component, View, bootstrap} from 'app/node-modules/angular2/angular2';
//import {RouteConfig, Router} from 'app/node-modules/angualar2/router';

@Component({
    selector : 'app'
})
@View({
    template '<h1>Hello {{ name }}</h1>'
})

class AppConponent{
    name : string;

    constructor(){
        this.name = 'Roman';
    }
}

bootatrap(AppComponent);