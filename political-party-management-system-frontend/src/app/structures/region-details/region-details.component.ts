import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Region } from 'src/app/model/Region';
import { Constituency } from 'src/app/model/Constituency';
import { RegionService } from 'src/app/services/region.service';
import { ConstituencyService } from 'src/app/services/constituency.service';
import { CreateStructureComponent } from '../create-structure/create-structure.component';

@Component({
  selector: 'app-region-details',
  templateUrl: './region-details.component.html',
  styleUrls: ['./region-details.component.css']
})
export class RegionDetailsComponent implements OnInit {

  @Input()
  region: Region;

  constituency: string;
  hidden: boolean;

  constituencyList: Observable<Constituency[]>;

  constructor(private regionService: RegionService, private constituencyService: ConstituencyService, private structures: CreateStructureComponent) { }

  ngOnInit() {
    this.hidden = false;
    this.getConstituencies();
  }

  addConstituency() {
    console.log(this.region);
    console.log({
      name: this.constituency,
      region: this.region.id
    });
    this.constituencyService.createConstituency(this.region, this.constituency).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
    this.constituency = '';
    this.getConstituencies();
  }

  updateRegion() {
    this.regionService.updateRegion(this.region.id, this.region).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
      this.getConstituencies();
  }

  deleteRegion() {
    this.regionService.deleteRegion(this.region.id).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
      this.hidden = true;
  }

  getConstituencies() {
    this.constituencyList = this.constituencyService.getConstituencyByRegion(this.region.name);
    console.log(this.constituencyList);
  }


}
