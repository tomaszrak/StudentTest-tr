/**
 * Created by ROLO on 27.01.2016.
 */



class App{
    constructor (@Inject(nameService) Nameservice){
        this.nameService = NameService.getName();
    }
}