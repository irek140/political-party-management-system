import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionVicePresidentPanelComponent } from './region-vice-president-panel.component';

describe('RegionVicePresidentPanelComponent', () => {
  let component: RegionVicePresidentPanelComponent;
  let fixture: ComponentFixture<RegionVicePresidentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionVicePresidentPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionVicePresidentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
