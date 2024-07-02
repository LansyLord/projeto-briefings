import { Briefing } from "./Briefing";

export class Cliente{

    id:number=0;
    name:string='';
    email:string='';
    briefings: Briefing[] = [];
    countBriefings: number = this.briefings.length;
}
