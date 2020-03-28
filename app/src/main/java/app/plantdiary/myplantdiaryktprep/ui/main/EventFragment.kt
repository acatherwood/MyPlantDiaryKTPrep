package app.plantdiary.myplantdiaryktprep.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import app.plantdiary.myplantdiaryktprep.R
import app.plantdiary.myplantdiaryktprep.dto.Event
import app.plantdiary.myplantdiaryktprep.dto.Specimen
import kotlinx.android.synthetic.main.event_fragment.*
import kotlinx.android.synthetic.main.rowlayout.*

class EventFragment : DiaryFragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var specimen: Specimen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        // TODO: Use the ViewModel
        specimen = viewModel.specimen

        btnSaveEvent.setOnClickListener {
            saveEvent()

        }

        rcyEvents.hasFixedSize()
        rcyEvents.layoutManager = LinearLayoutManager(context)
        rcyEvents.itemAnimator = DefaultItemAnimator()
        rcyEvents.adapter = EventsAdapter(specimen.events, R.layout.rowlayout)

    }

    private fun saveEvent() {
        var event = Event()
        var date = edtDate.text.toString()
        event.type = actEventType.toString()
        event.description = edtDescription.text.toString()
        event.quantity = edtQuantity.text.toString().toDoubleOrNull() ?: 0.0
        specimen.events.add(event)
        rcyEvents.adapter?.notifyDataSetChanged()
    }

    inner class EventsAdapter(val events: List<Event>, val itemLayout: Int) : RecyclerView.Adapter<EventViewHolder>() {
        /**
         * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
         * an item.
         *
         *
         * This new ViewHolder should be constructed with a new View that can represent the items
         * of the given type. You can either create a new View manually or inflate it from an XML
         * layout file.
         *
         *
         * The new ViewHolder will be used to display items of the adapter using
         * [.onBindViewHolder]. Since it will be re-used to display
         * different items in the data set, it is a good idea to cache references to sub views of
         * the View to avoid unnecessary [View.findViewById] calls.
         *
         * @param parent The ViewGroup into which the new View will be added after it is bound to
         * an adapter position.
         * @param viewType The view type of the new View.
         *
         * @return A new ViewHolder that holds a View of the given view type.
         * @see .getItemViewType
         * @see .onBindViewHolder
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return EventViewHolder(view)
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            return events.size
        }

        /**
         * Called by RecyclerView to display the data at the specified position. This method should
         * update the contents of the [ViewHolder.itemView] to reflect the item at the given
         * position.
         *
         *
         * Note that unlike [android.widget.ListView], RecyclerView will not call this method
         * again if the position of the item changes in the data set unless the item itself is
         * invalidated or the new position cannot be determined. For this reason, you should only
         * use the `position` parameter while acquiring the related data item inside
         * this method and should not keep a copy of it. If you need the position of an item later
         * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
         * have the updated adapter position.
         *
         * Override [.onBindViewHolder] instead if Adapter can
         * handle efficient partial bind.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         * item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            val event = events.get(position)
            holder.updateEvent(event)
        }

    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgEventThumbnail : ImageView = itemView.findViewById(R.id.imgEventThumbnail)
        private var lblEventInfo: TextView = itemView.findViewById(R.id.lblEventInfo)

        fun updateEvent(event: Event) {
            lblEventInfo.text = event.toString()
        }
    }

}