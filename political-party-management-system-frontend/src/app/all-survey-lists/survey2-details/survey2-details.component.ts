import { Component, OnInit, Input } from '@angular/core';
import { Survey2 } from 'src/app/model/surveys/survey2';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey2-details',
  templateUrl: './survey2-details.component.html',
  styleUrls: ['./survey2-details.component.css']
})
export class Survey2DetailsComponent implements OnInit {

  @Input()
  survey2: Survey2;

  hidden: boolean;

  tooday: Date;
  timeOkay: boolean;
  surveyEndDate: Date;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey2.id + ' ' + this.survey2.question);
    this.tooday = new Date();
  }


  chooseOption1() {
    this.surveyService.vote1(this.survey2.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey2.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2(this.survey2.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  showSuccessAlert() {
    this.toastr.success('Dziękujemy za oddanie głosu!', 'SUKCES!');
  }

}
