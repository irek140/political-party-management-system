import { Component, OnInit, Input } from '@angular/core';
import { Survey4 } from 'src/app/model/surveys/Survey4';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey4-details',
  templateUrl: './survey4-details.component.html',
  styleUrls: ['./survey4-details.component.css']
})
export class Survey4DetailsComponent implements OnInit {

  @Input()
  survey4: Survey4;

  hidden: boolean;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey4.id + ' ' + this.survey4.question);
  }

  chooseOption1() {
    this.surveyService.vote1Survey4(this.survey4.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey4.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2Survey4(this.survey4.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption3() {
    this.surveyService.vote3Survey4(this.survey4.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption4() {
    this.surveyService.vote4Survey4(this.survey4.id).subscribe(
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
