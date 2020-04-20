import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionPresidentPanelComponent } from './region-president-panel.component';

describe('RegionPresidentPanelComponent', () => {
  let component: RegionPresidentPanelComponent;
  let fixture: ComponentFixture<RegionPresidentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionPresidentPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionPresidentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
