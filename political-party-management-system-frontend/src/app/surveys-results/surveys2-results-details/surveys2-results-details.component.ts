import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { SurveysResultsListComponent } from '../surveys-results-list/surveys-results-list.component';
import { SurveyService } from 'src/app/services/survey.service';
import { Survey2 } from 'src/app/model/surveys/survey2';
import { UserService } from 'src/app/services/user.service';
import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-surveys2-results-details',
  templateUrl: './surveys2-results-details.component.html',
  styleUrls: ['./surveys2-results-details.component.css']
})
export class Surveys2ResultsDetailsComponent implements OnInit {

  @Input()
  survey2: Survey2;

  id: number;
  hidden: boolean;

  view: any[] = [700, 400];
  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  legendPosition: string = "below";

  colorScheme = {
    domain: ["#5AA454", "#A10A28", "#C7B42C", "#AAAAAA"]
  };

  surveyObject: any[];

  numberOfVotes: number;

  tooday: string;

  me: User;

  @ViewChild('content', {static: false}) content: ElementRef;

  constructor(private surveyService: SurveyService, private userService: UserService, private surveysResultsList: SurveysResultsListComponent) { }

  ngOnInit() {
    console.log(this.survey2.id + ' ' + this.survey2.question);
    this.tooday = new Date().toLocaleString();
    this.getMe();
    this.getMyInformation();
    this.hidden = false;
    this.surveyObject = [
      {
        "name": this.survey2.answer1,
        "value": this.survey2.value1
      }, {
        "name": this.survey2.answer2,
        "value": this.survey2.value2
      }
    ];
    this.numberOfVotes = this.survey2.value1 + this.survey2.value2;
  }

  getMyInformation() {
    this.userService.getMe().subscribe(
      user => {
      this.me = user;
      }
    );
  }

  print() {
    let doc = new jsPDF();
    doc.setFont('helvetica');
    doc.setFontType('bold');
    doc.setFontSize(25);
    doc.text(95, 40, 'Raport');
    //doc.addFileToVFS("abhafont.ttf");
    //doc.addFont('abhafont', "Abha", "normal", "Identity-H");
    doc.setFontType('normal');
    doc.setFontSize(13);
    doc.text(20, 60, 'Autor: ' + this.survey2.owner.name + ' ' + this.survey2.owner.surname);
    doc.text(20, 70, 'Glosowanie od: ' + this.survey2.created);
    doc.text(20, 80, 'Glosowanie do: ' + this.survey2.enddate);
    doc.text(20, 90, 'Data utworzenia raportu: ' + this.tooday);
    doc.text(20, 110, 'Tresc pytania: ');
    doc.text(20, 120, this.survey2.question);

    const votes = this.survey2.value1 + this.survey2.value2;

    doc.autoTable({
      startY: 140,
      head: [['Odpowiedz', 'Liczba glosow', 'Procent']],
      body: [
        [this.survey2.answer1, this.survey2.value1, (this.survey2.value1 / votes) * 100],
        [this.survey2.answer2, this.survey2.value2, (this.survey2.value2 / votes) * 100],
        // ...
      ],
    });

    let specialElementHandlers = {
      '#editor': function(element, renderer) {
        return true;
      }
    };

    let content = this.content.nativeElement;
    doc.fromHTML(content.innerHTML, 15, 15, {
      'width': 150,
      'elementHandlers': specialElementHandlers
    });

    doc.save('raport.pdf');
  }

  onSelect(data): void {
    console.log("Item clicked", JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log("Activate", JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log("Deactivate", JSON.parse(JSON.stringify(data)));
  }

  getMe() {
    this.userService.getMe().subscribe(
      user => {
      this.id = user.id;
      }
    );
  }

  deleteSurvey() {
    this.hidden = true;
    this.surveyService.deleteSurveys2(this.survey2.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  generateRaport() {
    this.surveyService.generateSurveys2Raport(this.survey2.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

}
