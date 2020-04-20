import { Component, OnInit, Input } from '@angular/core';
import { Constituency } from '../../model/Constituency';
import { RegionDetailsComponent } from '../region-details/region-details.component';
import { ConstituencyService } from '../../services/constituency.service';
import { Observable } from 'rxjs';
import { Region } from '../../model/Region';
import { RegionService } from '../../services/region.service';

@Component({
  selector: 'app-constituency-details',
  templateUrl: './constituency-details.component.html',
  styleUrls: ['./constituency-details.component.css']
})
export class ConstituencyDetailsComponent implements OnInit {

  @Input()
  con: Constituency;

  constituency: Constituency;
  regions: Observable<Region[]>;

  regionsStringArray: string[];

  parents: string[];
  newRegion: string;
  updateConstituency: Constituency;

  hiddenDiv: boolean;

  constructor(private constituencyService: ConstituencyService, private regionService: RegionService, private regionList: RegionDetailsComponent) { }

  ngOnInit() {
    this.hiddenDiv = false;
    this.getRegions();
    this.constituency = this.con;
    this.initUpdateConstituency();
    this.newRegion = this.updateConstituency.parent;
  }

  initUpdateConstituency() {
    this.updateConstituency = {
      id: this.constituency.id,
      name: this.constituency.name,
      region: this.constituency.region,
      parent: this.constituency.parent
    };
  }

  updateConstituencyMethod() {
    console.log(this.constituency.name);
    console.log(this.newRegion);
    console.log(this.updateConstituency);
    this.updateConstituency.parent = this.newRegion;
    this.constituencyService.updateConstituency(this.constituency.id, this.updateConstituency).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
    this.getRegions();
  }

  removeConstituency() {
    this.hiddenDiv = true;
    this.constituencyService.deleteConstituency(this.con.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  getRegions() {
    this.regions = this.regionService.getRegionList();
  }

}
